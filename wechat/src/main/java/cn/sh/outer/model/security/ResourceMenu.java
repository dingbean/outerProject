package cn.sh.outer.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ResourceMenu
 * 功能菜单
 * @author dk
 * @date 2016/10/9
 */
public class ResourceMenu implements Serializable {
    private int id;         //主键
    private int parentId;   //父级菜单id
    private ResourceMenu parent;	// 父级菜单
    private String parentIds; // 所有父级编号
    private String name; 	// 名称
    private String href; 	// 链接
    private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon; 	// 图标
    private Integer sort; 	// 排序
    private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
    private String permission; // 权限标识
    private String status;//可用标识
    private String creater; //创建者
    private Date createTime;
    private Date updateTime;
    private String updater; //更新者
    private String remark;
    private List<ResourceMenu> children = new ArrayList<ResourceMenu>();
    private int userId;
    private int roleId;


    public ResourceMenu(){
        super();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public ResourceMenu getParent() {
        return parent;
    }

    public void setParent(ResourceMenu parent) {
        this.parent = parent;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * 递归获取所有节点的子节点
     * @param list   递归完成后的list
     * @param sourcelist  需要递归的list
     * @param parentId    递归开始的父节点
     * @param cascade     是否级联递归
     */
    public static void sortList(List<ResourceMenu> list, List<ResourceMenu> sourcelist, int parentId, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            ResourceMenu e = sourcelist.get(i);
            if ( e.getParentId() >= 0
                    && e.getParentId() == parentId){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        ResourceMenu child = sourcelist.get(j);
                        if ( child.getParentId() >= 0
                                && child.getParentId()==(e.getId())){

                            sortList(e.getChildren(), sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static String getRootId(){
        return "1";
    }





    public List<ResourceMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceMenu> children) {
        this.children = children;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
