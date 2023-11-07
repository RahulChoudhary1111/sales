package com.sales.specifications;

import com.sales.entities.User;
import com.sales.entities.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {


    /**
     * there we create some predicates which is use for filters
     */


    public static Specification<User> hasUserType(String userType) {
        return (root, query, criteriaBuilder) -> {
            if (userType == null) return null;
            return criteriaBuilder.equal(root.get(User_.USER_TYPE), userType);
        };
    }

    public static Specification<User> isUserId(Integer wholesaleId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(User_.ID), wholesaleId);
        };
    }


    public static Specification<User> containsName(String UserName) {
        return (root, query, criteriaBuilder) -> {
            if (UserName == null) return null;
            return criteriaBuilder.like(root.get(User_.USERNAME), "%" + UserName + "%");
        };
    }

    public static Specification<User> greaterThanOrEqualFromDate(Long fromDate) {
        return (root, query, criteriaBuilder) -> {
            if (fromDate == null) return  null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get(User_.CREATED_AT), fromDate);
        };
    }

    public static Specification<User> lessThanOrEqualToToDate(Long toDate) {
        return (root, query, criteriaBuilder) -> {
            if (toDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get(User_.CREATED_AT), toDate);
        };
    }


    public static Specification<User> isStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.equal(root.get(User_.STATUS),status);
        };
    }

    public static Specification<User> hasSlug(String slug) {
        return (root, query, criteriaBuilder) -> {
            if (slug == null) return null;
            return criteriaBuilder.equal(root.get(User_.SLUG), slug);
        };
    }


}
