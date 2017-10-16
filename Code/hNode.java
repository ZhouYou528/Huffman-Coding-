//package huffman;

class hNode {

    hNode left;
    hNode right;
    String data;

    public hNode(){

    }
    public hNode(String data){
        this.data = data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
}
