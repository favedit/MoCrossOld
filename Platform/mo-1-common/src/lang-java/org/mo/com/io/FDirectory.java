package org.mo.com.io;

import java.io.File;
import org.mo.com.lang.FObjects;

public class FDirectory
{
   private File _directory;

   public FDirectory(){
   }

   public FDirectory(File directory){
      _directory = directory;
   }

   public FDirectory(String directory){
      _directory = new File(directory);
   }

   public String absolutePath(){
      return _directory.getAbsolutePath();
   }

   public void delete(){
      delete(_directory);
      _directory.delete();
   }

   private void delete(File directory){
      for(File file : directory.listFiles()){
         if(file.isDirectory()){
            delete(file);
            file.delete();
         }
         if(file.isFile()){
            file.delete();
         }
      }
   }

   public boolean isName(String name){
      return _directory.getName().equals(name);
   }

   public FDirectory[] listDirectory(){
      FObjects<FDirectory> directories = new FObjects<FDirectory>(FDirectory.class);
      for(File file : _directory.listFiles()){
         if(file.isDirectory()){
            directories.push(new FDirectory(file));
         }
      }
      return directories.toObjects();
   }

   public FDirectory[] listFiles(){
      FObjects<FDirectory> directories = new FObjects<FDirectory>(FDirectory.class);
      for(File file : _directory.listFiles()){
         if(file.isFile()){
            directories.push(new FDirectory(file));
         }
      }
      return directories.toObjects();
   }

   public String name(){
      return _directory.getName();
   }

   @Override
   public String toString(){
      return _directory.toString();
   }
}
