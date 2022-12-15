package com.example.demo.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseDto<T> implements Serializable
{
	private T id;
}
