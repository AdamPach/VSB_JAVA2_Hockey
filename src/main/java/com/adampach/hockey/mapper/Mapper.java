package com.adampach.hockey.mapper;

public interface Mapper <TFrom, TTo>{
    TTo map(TFrom from);
}
