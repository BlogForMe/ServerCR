package org.example.server.controllers;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.server.pojo.Poi;
import org.example.server.service.IPoiService;
import org.example.server.vo.PoiVo;
import org.example.server.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/poi")
@Slf4j
public class PoiController {
/*
    //方式1 : 通过map直接调用
    @Autowired
    private PoiMapper poiMapper;*/

    //方式2 : 通过service调用map
    @Autowired
    IPoiService poiService;

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("my info , pageNum ={}  pageSize ={}", pageNum, pageSize);
        Page page = new Page(pageNum, pageSize);
        IPage pageResult = poiService.page(page);
        List<Poi> poiList = pageResult.getRecords();
        List<PoiVo> poiVos = (List<PoiVo>) pageResult.getRecords().stream().map(poi -> {
            PoiVo poiVo = new PoiVo();
            BeanUtils.copyProperties(poi, poiVo);
            return poiVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(poiVos);
        return Result.success(pageResult);
    }

    @GetMapping("/response/headers")
    public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header",
                "Value-ResponseEntityBuilderWithHttpHeaders");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Response with header using ResponseEntity");
    }


    /**
     * http://localhost:8080/poi/detail/1
     *
     * @param id = 1
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result<Poi> detail(@PathVariable int id, HttpServletResponse response) {
        log.info("poi detail , id={}", id);
//        Poi poi = poiMapper.selectById(id); // 方式1
        response.addHeader("Baeldung-Example-Header", "Value-HttpServletResponse");
        Poi poi = poiService.getById(id); // 方式2
        return Result.success(poi);
    }


    @PostMapping("/detail/post")
    public Result<Poi> postDetail(@RequestBody PoiVo poiVo, HttpServletResponse response) {
        log.info("poi detail , id={}", poiVo);
//        Poi poi = poiMapper.selectById(id); // 方式1
//        response.addHeader("Baeldung-Example-Header", "Value-HttpServletResponse");
        Poi poi = poiService.getById(poiVo.id); // 方式2
        return Result.success(poi);
    }


    @PostMapping("/add")
    public String add(@RequestBody PoiVo poiVo) {
        log.info("poi add ,name={}", poiVo.name);
        Poi poi = new Poi();
        poi.setCoverUrl("https://raw.githubusercontent.com/BlogForMe/ImageServer/main/OKHTTP/image-20211122164303152.png");
        BeanUtils.copyProperties(poiVo, poi);
        poiService.save(poi);
        return "this is add";
    }


    @PutMapping("/edit")
    public String edit(@RequestBody PoiVo poi) {
        log.info("poi add ,name={} description={}", poi.name, poi.description);
        return "this is edit";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        log.info("poi delete , id={}", id);
        return "this is delete";
    }


}
