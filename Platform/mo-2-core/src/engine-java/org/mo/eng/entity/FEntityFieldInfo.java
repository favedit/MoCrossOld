package org.mo.eng.entity;

import org.mo.eng.entity.face.AField;
import org.mo.eng.entity.face.AFieldAccess;
import org.mo.eng.entity.face.EFieldKey;
import org.mo.eng.entity.face.EFieldType;

public class FEntityFieldInfo
{
   private final boolean _delete;

   private final boolean _empty;

   private final boolean _insert;

   private final EFieldKey _key;

   private final String _name;

   private final EFieldType _type;

   private final boolean _update;

   public FEntityFieldInfo(AField field,
                           AFieldAccess access){
      _name = field.name();
      _type = field.type();
      _key = field.key();
      _empty = field.empty();
      _insert = access.insert();
      _update = access.update();
      _delete = access.delete();
   }

   public boolean delete(){
      return _delete;
   }

   public boolean empty(){
      return _empty;
   }

   public boolean insert(){
      return _insert;
   }

   public EFieldKey key(){
      return _key;
   }

   public String name(){
      return _name;
   }

   public EFieldType type(){
      return _type;
   }

   public boolean update(){
      return _update;
   }
}
