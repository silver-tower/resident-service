package com.silvertown.resident_service.residence.domain.model;

import java.util.HashSet;
import java.util.Set;

public enum ResidenceStatus {
    ACTIVE {
        @Override
        public boolean canTransitionTo(ResidenceStatus status) {
            Set<ResidenceStatus> availableTransition = new HashSet<>() {{
                add(INACTIVE);
                add(UNAVAILABLE);
            }};
            return availableTransition.contains(status);
        }
    },
    INACTIVE {
        @Override
        public boolean canTransitionTo(ResidenceStatus status) {

            Set<ResidenceStatus> availableTransition = new HashSet<>() {{
                add(PENDING);
            }};
            return availableTransition.contains(status);
        }
    },
    PENDING {
        @Override
        public boolean canTransitionTo(ResidenceStatus status) {

            Set<ResidenceStatus> availableTransition = new HashSet<>() {{
                add(ACTIVE);
                add(INACTIVE);
            }};
            return availableTransition.contains(status);
        }
    },
    UNAVAILABLE {
        @Override
        public boolean canTransitionTo(ResidenceStatus status) {

            Set<ResidenceStatus> availableTransition = new HashSet<>() {{
                add(INACTIVE);
            }};
            return availableTransition.contains(status);
        }
    },
    DELETED {
        @Override
        public boolean canTransitionTo(ResidenceStatus status) {

            Set<ResidenceStatus> availableTransition = new HashSet<>() {{
                add(INACTIVE);
                add(DELETED);
            }};
            return availableTransition.contains(status);
        }
    },
    ;

    public abstract boolean canTransitionTo(ResidenceStatus newStatus);
}
