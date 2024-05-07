package org.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.server.mapper.PoiMapper;
import org.example.server.pojo.Poi;
import org.example.server.service.IPoiService;
import org.springframework.stereotype.Service;

@Service
public class PoiServiceImpl extends ServiceImpl<PoiMapper, Poi> implements IPoiService {
}
