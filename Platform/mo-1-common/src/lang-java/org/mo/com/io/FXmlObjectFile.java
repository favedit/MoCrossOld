package org.mo.com.io;

public class FXmlObjectFile
      extends FStringFile
{
   public FXmlObjectFile(){
   }
   //   public FXmlObjectFile(File file){
   //      _filename = file.getAbsolutePath();
   //   }
   //
   //   public FXmlObjectFile(String filename){
   //      _filename = filename;
   //   }
   //
   //   @SuppressWarnings("unchecked")
   //   public <V> V readObject(){
   //      //      super.loadFromFile(_filename);
   //      //      try{
   //      //         XStream xstream = new XStream();
   //      //         return (V)xstream.fromXML(toString());
   //      //      }catch(Exception e){
   //      //         throw new FFatalError(e, "Read object from file error. (file={0})", _filename);
   //      //      }
   //   }
   //
   //   public void writeObject(Object item){
   //      clear();
   //      //      try{
   //      //         XStream xstream = new XStream();
   //      //         append(xstream.toXML(item));
   //      //      }catch(Exception e){
   //      //         throw new FFatalError(e, "Write object to file error. (file={0}, object={1})", _filename, item);
   //      //      }
   //      //      saveToFile(_filename);
   //   }
}
