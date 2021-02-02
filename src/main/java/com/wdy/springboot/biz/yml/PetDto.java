package com.wdy.springboot.biz.yml;

import lombok.Data;

/**
 * 宠物
 *
 * @author wgch
 * @date 2020/7/3
 */
@Data
class PetDto {
    private String nickName;
    private String strain;
}
