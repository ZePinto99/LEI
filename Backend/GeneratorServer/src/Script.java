import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Script {
    int id;
    String[] tags;
    String script;
    //Map {key_word,[index,index,...]}
    Map<String, List> keyWords_Index;

    public Script(int id, String[] tags, String script, String keyWords){
        setId(id);
        setTags(tags);
        setScript(script);
        setKeyWords_Index(extractKeyWords_MAP(keyWords));
    }

    public Map<String, List> extractKeyWords_MAP(String keywords){
        String[] keyValues = keywords.split("###");
        Map<String, List> ret = new HashMap<>();
        List indexes;
        String keyword;

        //for key:index,index,...
        for (String pair : keyValues){
            String[] keyValue = pair.split(":");
            keyword = keyValue[0];
            indexes = new ArrayList();
            //for index, index...
            for (String index : keyValue[1].split(",")){
                indexes.add(Integer.parseInt(index));
            }
            ret.put(keyword, indexes);
        }
        return ret;
    }

    public boolean hasTag(String tag){
        for(int i=0; i<tags.length; i++)
            if(tags[i].equals(tag)) return true;
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String[] getTags() {
        return tags;
    }

    public String getScript() {
        return script;
    }

    public int getId() {
        return id;
    }

    public void setKeyWords_Index(Map<String, List> keyWords_Index) {
        this.keyWords_Index = keyWords_Index;
    }

    public Map<String, List> getKeyWords_Index() {
        return keyWords_Index;
    }

    public Object[] keyIndexes(String key){
        return keyWords_Index.get(key).toArray();
    }
}
