package org.kevvlvl.auditapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class AuditServiceImpl implements AuditService {

    private static final Logger LOG = LoggerFactory.getLogger(AuditServiceImpl.class);

    public AuditServiceImpl() {
    }

    public void auditAction(String returnedPayload) {
        LOG.info("auditAction - AUDIT the returned Payload: {}", returnedPayload);
    }
}
