package com.example.demo.serviceimpl.mapper.base;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapConfig
{
}
