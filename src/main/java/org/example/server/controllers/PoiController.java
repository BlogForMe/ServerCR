package org.example.server.controllers;


import lombok.extern.slf4j.Slf4j;
import org.example.server.mapper.PoiMapper;
import org.example.server.vo.PoiVo;
import org.example.server.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/poi")
@Slf4j
public class PoiController {

    @Autowired
    private PoiMapper poiMapper;

    @GetMapping("/list")
    public Result<List<PoiVo>> list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("my info , pageNum ={}  pageSize ={}", pageNum, pageSize);
        var poiVo1 = new PoiVo();
        poiVo1.name ="list";
        poiVo1.description = "this is detail";

        var poiVo2 = new PoiVo();
        poiVo2.name ="list";
        poiVo2.description = "this is detail";
        ArrayList<PoiVo> poiVos = new ArrayList<>();
        poiVos.add(poiVo1);
        poiVos.add(poiVo2);
        Result<List<PoiVo>> poiVoResult = new Result<>();
        poiVoResult.msg="success";
        poiVoResult.code=0;
        poiVoResult.data = poiVos;
        return poiVoResult;
    }


    @GetMapping("/detail/{id}")
    public Result<PoiVo> detail(@PathVariable int id) {
        log.info("poi detail , id={}", id);
        var poiVo = new PoiVo();
        poiVo.name ="list";
        poiVo.description = "this is detail";

        Result<PoiVo> poiVoResult = new Result<>();
        poiVoResult.msg="success";
        poiVoResult.code=0;
        poiVoResult.data = poiVo;
        return poiVoResult;
    }


    @PostMapping("/add")
    public String add(@RequestBody PoiVo poi) {
        log.info("poi add ,name={}",poi.name);
        return "this is add";
    }


    @PutMapping("/edit")
    public String edit(@RequestBody PoiVo poi) {
        log.info("poi add ,name={} description={}" ,poi.name,poi.description);
        return "this is edit";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        log.info("poi delete , id={}", id);
        return "this is delete";
    }


}
