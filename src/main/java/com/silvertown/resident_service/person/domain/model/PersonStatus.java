package com.silvertown.resident_service.person.domain.model;

import java.util.HashSet;
import java.util.Set;

public enum PersonStatus {
    ACTIVE {
        @Override
        public boolean canTransitionTo(PersonStatus status) {
            Set<PersonStatus> availableTransition = new HashSet<>() {{
                add(INACTIVE);
                add(DELETED);
            }};

            return availableTransition.contains(status);
        }
    },
    INACTIVE {
        @Override
        public boolean canTransitionTo(PersonStatus status) {
            Set<PersonStatus> availableTransition = new HashSet<>() {{
                add(ACTIVE);
                add(DELETED);
            }};
            return availableTransition.contains(status);
        }
    }, DELETED {
        @Override
        public boolean canTransitionTo(PersonStatus status) {
            return false;
        }
    },
    ;
    public abstract boolean canTransitionTo(PersonStatus newStatus);

}
