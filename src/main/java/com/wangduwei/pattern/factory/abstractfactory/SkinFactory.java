package com.wangduwei.pattern.factory.abstractfactory;

//界面皮肤工厂接口：抽象工厂  
interface SkinFactory {  
  public Button createButton();  
  public TextField createTextField();  
  public ComboBox createComboBox();  
}  
