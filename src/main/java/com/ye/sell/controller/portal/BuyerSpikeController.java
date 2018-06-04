package com.ye.sell.controller.portal;

import com.ye.sell.service.SpikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/spike")
public class BuyerSpikeController {

    private SpikeService spikeService;

    @Autowired
    public void setSpikeService(SpikeService spikeService) {
        this.spikeService = spikeService;
    }

    @GetMapping("/query")
    public String query() {
        return spikeService.query();
    }

    @GetMapping("/go")
    public String go() {
        return spikeService.go();
    }
}
