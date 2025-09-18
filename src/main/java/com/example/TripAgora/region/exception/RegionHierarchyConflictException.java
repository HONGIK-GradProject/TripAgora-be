package com.example.TripAgora.region.exception;

import com.example.TripAgora.common.code.RegionErrorCode;

public class RegionHierarchyConflictException extends RegionException {
    public RegionHierarchyConflictException() {
        super(RegionErrorCode.REGION_HIERARCHY_CONFLICT);
    }
}