package com.wdy.crawl.link;

/**
 * @author wgch
 */
public interface LinkFilter {
    boolean accept(String url);
}
