package cn.sh.outer.model;

import java.math.BigDecimal;

public class UserRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_USER_ROLE.ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    private BigDecimal id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_USER_ROLE.USER_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    private BigDecimal userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_USER_ROLE.ROLE_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    private BigDecimal roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_USER_ROLE.ID
     *
     * @return the value of T_USER_ROLE.ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_USER_ROLE.ID
     *
     * @param id the value for T_USER_ROLE.ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_USER_ROLE.USER_ID
     *
     * @return the value of T_USER_ROLE.USER_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public BigDecimal getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_USER_ROLE.USER_ID
     *
     * @param userId the value for T_USER_ROLE.USER_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_USER_ROLE.ROLE_ID
     *
     * @return the value of T_USER_ROLE.ROLE_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public BigDecimal getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_USER_ROLE.ROLE_ID
     *
     * @param roleId the value for T_USER_ROLE.ROLE_ID
     *
     * @mbggenerated Wed Jun 17 16:04:30 CST 2015
     */
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
}