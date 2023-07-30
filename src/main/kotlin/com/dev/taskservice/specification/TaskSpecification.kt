package com.dev.taskservice.specification

import com.dev.taskservice.util.SearchTextHelper
import com.dev.taskservice.entity.Task
import com.dev.taskservice.model.metadata.Task_
import com.dev.taskservice.model.request.task.TaskFilter
import org.springframework.data.jpa.domain.Specification
import java.util.Date
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class TaskSpecification(private val taskFilter: TaskFilter): Specification<Task> {
    override fun toPredicate(root: Root<Task>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
        val predicates = mutableListOf<Predicate>()

        with(taskFilter) {
            titleSearchText?.let {
                val preparedSearchText = SearchTextHelper.prepareSearchText(it)
                predicates.add(cb.like(cb.lower(root.get(Task_.TITLE)), preparedSearchText))
            }

            createdStartDate?.let {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Task_.CREATE_DATE), Date(it)))
            }

            createdEndDate?.let {
                predicates.add(cb.lessThanOrEqualTo(root.get(Task_.CREATE_DATE), Date(it)))
            }

            dueStartDate?.let {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Task_.DUE_DATE), Date(it)))
            }

            dueEndDate?.let {
                predicates.add(cb.lessThanOrEqualTo(root.get(Task_.DUE_DATE), Date(it)))
            }

            isCompleted?.let {
                predicates.add(cb.equal(root.get<Boolean>(Task_.IS_COMPLETED), it))
            }

            isDeleted?.let {
                predicates.add(cb.equal(root.get<Boolean>(Task_.IS_DELETED), it))
            }
        }


        return cb.and(*predicates.toTypedArray())
    }


}