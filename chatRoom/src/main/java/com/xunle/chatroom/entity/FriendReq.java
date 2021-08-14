package com.xunle.chatroom.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xunle
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FriendReq对象", description="")
public class FriendReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "发请求用户id")
    private String userReqId;

    @ApiModelProperty(value = "被请求用户id")
    private String friendReqId;

    @ApiModelProperty(value = "被请求用户名")
    private String friendReqUsername;

    @ApiModelProperty(value = "请求信息")
    private String reqMessage;

    @ApiModelProperty(value = "1表示接受请求，0表示拒绝", hidden = true)
    private Integer accept;

    @ApiModelProperty(value = "状态为1表示已处理，为0表示未处理", hidden = true)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间", hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;


}
