import nltk
from nltk.stem.lancaster import LancasterStemmer
stemmer = LancasterStemmer()

import numpy
import tflearn
import tensorflow
#from tensorflow.python.framework import ops
import random
import json
import pickle

#Lê o ficheiro "intents.json"
with open("intents.json") as file:
    data = json.load(file)
print("######## HELLO 0 ##############")
try:
    #Está a dar erro 
    x
    print("######## HELLO 1 ##############")
    with open("data.pickle", "rb") as f:
        words, labels, training, output = pickle.load(f)
except:
    print("######## HELLO 2 ##############")
    words = []
    labels = []
    #Guarda o pattern
    docs_x = []
    #Guarda o itent
    docs_y = []

    #Percorremos todos os dicionários
    for intent in data["intents"]:
        #Percorremos todos os "patterns"
        for pattern in intent["patterns"]:
            #Transformar uma palavra na sua raiz (para nos focarmos no significado)
            wrds = nltk.word_tokenize(pattern)
            #Colocamos todas as palavras (reduzidas à raiz) numa lista
            words.extend(wrds)
            docs_x.append(wrds)
            docs_y.append(intent["tag"])

        #Adicionar tags que não temos
        if intent["tag"] not in labels:
            labels.append(intent["tag"])

    #Quanta quantas palavras o modelo analisa
    words = [stemmer.stem(w.lower()) for w in words if w != "?"]
    #Remove os duplicados
    words = sorted(list(set(words)))

    labels = sorted(labels)

    #lista com bags of words. Que é uma lsita de 0's e 1's
    training = [] 
    #Lista de outputs. Que também são 0's e 1's
    output = []

    #Cria uma lista com 0's do tamanho do número de labels que temos
    out_empty = [0 for _ in range(len(labels))]

    #Percorremos as palavras e verificamos se elas existem no ficheiro intents
    for x, doc in enumerate(docs_x):
        bag = []

        wrds = [stemmer.stem(w.lower()) for w in doc]

        for w in words:
            if w in wrds:
                bag.append(1) #Se a palavra estiver em intents
            else:
                bag.append(0) #Se a palavra não estiver em intents

            output_row = out_empty[:]
            #Percorremos as labels, vemos onde a tag está nessa lista
            #Passamos o value na lista a 1
            output_row[labels.index(docs_y[x])] = 1

            training.append(bag)
            output.append(output_row)


    training = numpy.array(training)
    output = numpy.array(output)
    #with open("data.pickle", "wb") as f:
        #pickle.dump((words, labels, training, output), f)

#reset dos settings
#tensorflow.reset_default_graph()

########### Modelo ML ####################################
#Definir o tamnaho do input para o modelo
net = tflearn.input_data(shape=[None, len(training[0])])
#Adicionamos layers à nossa rede neuronal
net = tflearn.fully_connected(net, 8)
net = tflearn.fully_connected(net, 8)
#Permite obter probabilidades para cada output
net = tflearn.fully_connected(net, len(output[0]), activation="softmax")

net = tflearn.regression(net)
model = tflearn.DNN(net)
##########################################################
#Carregar o modelo ou treinar
try:
    #EStá a dar ERRO
    y
    model.load("model.tflearn")
except:
    model.fit(training, output, n_epoch=50, batch_size=8, show_metric=True)
    model.save("model.tflearn")


#Gera um bag of words
#-- A fazer --
#Neste momento estamos a fazer uma predict sobre todo o input
#O que queremos é fazer uma predict por key word!
#Ou seja chamar o modelo várias vezes para cada key word, ou grupo de key words
#Dificuldade ---> O que é uma key word
def bag_of_words(s, words):
    bag = [0 for _ in range(len(words))]

    s_words = nltk.word_tokenize(s)
    s_words = [stemmer.stem(word.lower()) for word in s_words]
    print("s_words")
    print(s_words)

    for se in s_words:
        for i, w in enumerate(words):
            if w == se:
                bag[i] = 1
    return numpy.array(bag)

def chat():
    print("Start talking with the bot (type quit to stop)!")
    while True:
        inp = input("You: ")
        if inp.lower() == "quit":
            break
        
        keyWords = inp.split()
        artical = ""
        #Returna os resultados(em probabiidades)
        for keyWord in keyWords:
            results = model.predict([bag_of_words(keyWord, words)])
            results = results[0]
            results_index = numpy.argmax(results)
            tag = labels[results_index]
            #Se tivermos uma confiança na resposta superior a 70%
            if results[results_index] > 0.7:
                for tg in data["intents"]:
                    if tg['tag'] == tag:
                        responses = tg['responses']

                #Escolhe uma das hipóteses aleatóriamente
                artical += str(random.choice(responses))
                print(artical)
                
            else:
                print("I didn't understand")
        
        print("Final Artical:")
        print(artical)


chat()