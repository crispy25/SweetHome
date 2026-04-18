package com.sweethome.backend.service.search;

import com.sweethome.backend.entity.Apartment;
import com.sweethome.backend.entity.House;
import com.sweethome.backend.entity.Property;
import com.sweethome.backend.service.dto.PropertySearchDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PropertySpecification {

    private static <T extends Comparable<? super T>> void addRange(List<Predicate> predicates, CriteriaBuilder cb, Path<T> path, T min, T max) {
        if (min != null)
            predicates.add(cb.greaterThanOrEqualTo(path, min));
        if (max != null)
            predicates.add(cb.lessThanOrEqualTo(path, max));
    }

    public static Specification<Property> toSpecification(PropertySearchDto req) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            final Root<Apartment> apartmentRoot = cb.treat(root, Apartment.class);
            final Root<House> houseRoot = cb.treat(root, House.class);

            addRange(predicates, cb, root.get("rooms"), req.getMinRooms(), req.getMaxRooms());
            addRange(predicates, cb, root.get("bathrooms"), req.getMinBathrooms(), req.getMaxBathrooms());
            addRange(predicates, cb, root.get("price"), req.getMinPrice(), req.getMaxPrice());
            addRange(predicates, cb, root.get("usableArea"), req.getMinUsableArea(), req.getMaxUsableArea());
            addRange(predicates, cb, root.get("constructionYear"), req.getMinConstructionYear(), req.getMaxConstructionYear());
            addRange(predicates, cb, root.get("createdAt"), req.getCreatedAfter(), req.getCreatedBefore());

            if (req.getPropertyType() != null)
                predicates.add(cb.equal(root.get("propertyType"), req.getPropertyType()));

            if (req.getMinFloor() != null || req.getMaxFloor() != null)
                addRange(predicates, cb, apartmentRoot.get("floor"), req.getMinFloor(), req.getMaxFloor());

            if (req.getComfort() != null)
                predicates.add(cb.equal(apartmentRoot.get("comfort"), req.getComfort()));

            if (req.getLayout() != null)
                predicates.add(cb.equal(apartmentRoot.get("layout"), req.getLayout()));

            if (req.getMinFloors() != null || req.getMaxFloors() != null)
                addRange(predicates, cb, houseRoot.get("floors"), req.getMinFloors(), req.getMaxFloors());

            if (req.getMinLandArea() != null || req.getMaxLandArea() != null)
                addRange(predicates, cb, houseRoot.get("landArea"), req.getMinLandArea(), req.getMaxLandArea());

            if (req.getHouseType() != null)
                predicates.add(cb.equal(houseRoot.get("houseType"), req.getHouseType()));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}