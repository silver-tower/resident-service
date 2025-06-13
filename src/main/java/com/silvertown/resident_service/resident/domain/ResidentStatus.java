package com.silvertown.resident_service.resident.domain;

import java.util.HashSet;
import java.util.Set;

public enum ResidentStatus {
    PENDING {
        @Override
        public boolean canTransitionTo(ResidentStatus status) {
            Set<ResidentStatus> availableTransition = new HashSet<>() {{
                add(APPROVED);
                add(REJECTED);
            }};
            return availableTransition.contains(status);
        }
    },
    APPROVED {
        @Override
        public boolean canTransitionTo(ResidentStatus status) {
            Set<ResidentStatus> availableTransition = new HashSet<>() {{
                add(DEACTIVATED);
                add(TERMINATED);
            }};
            return availableTransition.contains(status);
        }
    },
    REJECTED {
        @Override
        public boolean canTransitionTo(ResidentStatus status) {
            Set<ResidentStatus> availableTransition = new HashSet<>() {{
                add(PENDING);
            }};
            return availableTransition.contains(status);
        }
    },
    DEACTIVATED {
        @Override
        public boolean canTransitionTo(ResidentStatus status) {
            Set<ResidentStatus> availableTransition = new HashSet<>() {{
                add(APPROVED);
                add(TERMINATED);
            }};
            return availableTransition.contains(status);
        }
    }, TERMINATED {
        @Override
        public boolean canTransitionTo(ResidentStatus status) {
            return false;
        }
    };

    public abstract boolean canTransitionTo(ResidentStatus newStatus);
}
