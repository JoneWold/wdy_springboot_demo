package com.wdy.game.constant;

/**
 * 子弹方向
 *
 * @author wgch
 * @date 2020/6/19
 */
public enum Direction {
    /**
     * 基本4方向
     */
    DUp, DDown, DLeft, Dright, DStop,
    /**
     * 扩展4方向
     */
    DUpLeft, DUpright, DDownLeft, DDownRight,
    /**
     * 自动方向，主要用于子弹跟踪
     */
    DAuto;
}
