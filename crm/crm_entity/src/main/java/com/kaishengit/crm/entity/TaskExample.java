package com.kaishengit.crm.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdIsNull() {
            addCriterion("salesrecord_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdIsNotNull() {
            addCriterion("salesrecord_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdEqualTo(Integer value) {
            addCriterion("salesrecord_id =", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdNotEqualTo(Integer value) {
            addCriterion("salesrecord_id <>", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdGreaterThan(Integer value) {
            addCriterion("salesrecord_id >", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("salesrecord_id >=", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdLessThan(Integer value) {
            addCriterion("salesrecord_id <", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("salesrecord_id <=", value, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdIn(List<Integer> values) {
            addCriterion("salesrecord_id in", values, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdNotIn(List<Integer> values) {
            addCriterion("salesrecord_id not in", values, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdBetween(Integer value1, Integer value2) {
            addCriterion("salesrecord_id between", value1, value2, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andSalesrecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("salesrecord_id not between", value1, value2, "salesrecordId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Integer value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Integer value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Integer value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Integer> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andDoneIsNull() {
            addCriterion("done is null");
            return (Criteria) this;
        }

        public Criteria andDoneIsNotNull() {
            addCriterion("done is not null");
            return (Criteria) this;
        }

        public Criteria andDoneEqualTo(Boolean value) {
            addCriterion("done =", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotEqualTo(Boolean value) {
            addCriterion("done <>", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThan(Boolean value) {
            addCriterion("done >", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThanOrEqualTo(Boolean value) {
            addCriterion("done >=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThan(Boolean value) {
            addCriterion("done <", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThanOrEqualTo(Boolean value) {
            addCriterion("done <=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneIn(List<Boolean> values) {
            addCriterion("done in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotIn(List<Boolean> values) {
            addCriterion("done not in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneBetween(Boolean value1, Boolean value2) {
            addCriterion("done between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotBetween(Boolean value1, Boolean value2) {
            addCriterion("done not between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIsNull() {
            addCriterion("finsh_time is null");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIsNotNull() {
            addCriterion("finsh_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinshTimeEqualTo(Date value) {
            addCriterion("finsh_time =", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotEqualTo(Date value) {
            addCriterion("finsh_time <>", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeGreaterThan(Date value) {
            addCriterion("finsh_time >", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finsh_time >=", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeLessThan(Date value) {
            addCriterion("finsh_time <", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeLessThanOrEqualTo(Date value) {
            addCriterion("finsh_time <=", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIn(List<Date> values) {
            addCriterion("finsh_time in", values, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotIn(List<Date> values) {
            addCriterion("finsh_time not in", values, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeBetween(Date value1, Date value2) {
            addCriterion("finsh_time between", value1, value2, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotBetween(Date value1, Date value2) {
            addCriterion("finsh_time not between", value1, value2, "finshTime");
            return (Criteria) this;
        }

        public Criteria andReminderIsNull() {
            addCriterion("reminder is null");
            return (Criteria) this;
        }

        public Criteria andReminderIsNotNull() {
            addCriterion("reminder is not null");
            return (Criteria) this;
        }

        public Criteria andReminderEqualTo(Date value) {
            addCriterion("reminder =", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderNotEqualTo(Date value) {
            addCriterion("reminder <>", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderGreaterThan(Date value) {
            addCriterion("reminder >", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderGreaterThanOrEqualTo(Date value) {
            addCriterion("reminder >=", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderLessThan(Date value) {
            addCriterion("reminder <", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderLessThanOrEqualTo(Date value) {
            addCriterion("reminder <=", value, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderIn(List<Date> values) {
            addCriterion("reminder in", values, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderNotIn(List<Date> values) {
            addCriterion("reminder not in", values, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderBetween(Date value1, Date value2) {
            addCriterion("reminder between", value1, value2, "reminder");
            return (Criteria) this;
        }

        public Criteria andReminderNotBetween(Date value1, Date value2) {
            addCriterion("reminder not between", value1, value2, "reminder");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}