package com.wangduwei.spider

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

class JiKeTimeBlogProcessor : PageProcessor {
    private val site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(0)

    override fun process(page: Page) {
//        System.out.println(page.getRawText());
        page.addTargetRequests(page.html.links().regex(page.url.toString() + ".*").all())
    }

    override fun getSite(): Site = site


    companion object {
        private const val DEF_SITE = "http://47.74.184.161:10000/course/"

        @JvmStatic
        fun main(args: Array<String>) {
            Spider.create(JiKeTimeBlogProcessor()).addUrl(DEF_SITE)
                    .addPipeline(FilePipelineImpl()).thread(1).run()
        }
    }
}