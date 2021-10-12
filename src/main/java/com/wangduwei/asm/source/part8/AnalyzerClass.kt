package com.wangduwei.asm.source.part8

/*
本章介绍分析方法的 ASM 类型 API，它是基于树类型的 API。分析代码是一个很大的范畴，在本章中我们只介绍 ASM 中使用的算法.
想了解更多算法,可以找有关编译器的书籍进行了解.
代码分析的两种类型:数据流和控制流
数据流分析包括:对于一个方法的每条指令,计算其执行帧的状态.
控制流分析包括计算一个方法的控制流图，并对这个图进行分析。控制流图的节点为指令，如果指令 j 可以紧跟在 i 之后执行，则图的有向边将连接这两条指令 i→j。

数据流分析有两种类型：
- 正向分析：是指对于每条指令，根据执行帧在执行此条指令之前的状态，计算执行帧在这一指令执行后的状态。
- 反向分析：是指对于每条指令，根据执行帧在执行此指令之后的状态，计算执行帧在这一指令执行前的状态。
正向数据流分析的执行是对于一个方法的每个字节码指令，模拟其在执行帧上的执行，通常包括：
- 从栈中弹出值，合并它们，将结果压入栈中

看起来是 Java 虚拟机要做的事情,实际上是对于目标可能出现的参数值,模拟一个方法执行的所有路径。而不是一组特定参数执行的单一路径。

控制流分析的基础是方法的控制流图。

 */
class AnalyzerClass {
}