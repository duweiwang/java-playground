package com.wangduwei.spider

import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

class FilePipelineImpl : Pipeline {
    override fun process(resultItems: ResultItems, task: Task) {
        println("---")
    }
}