package com.github.mybsam12138.exception.system;

import com.github.mybsam12138.exception.base.BaseException;
import com.github.mybsam12138.exception.base.ErrorCode;

/**
 * Infrastructure-level exception.
 *
 * <p>
 * This exception represents failures caused by underlying infrastructure
 * dependencies rather than business logic or user behavior.
 * </p>
 *
 * <p>
 * Typical scenarios include:
 * <ul>
 *   <li>Database connection or query failures</li>
 *   <li>Cache (e.g. Redis) unavailability or timeout</li>
 *   <li>Message queue failures</li>
 *   <li>External service invocation errors</li>
 *   <li>File system or IO errors</li>
 * </ul>
 * </p>
 *
 * <p>
 * InfraException is usually mapped to a generic system error response
 * and should not expose internal details to clients.
 * </p>
 *
 * <p>
 * This exception is intended to be thrown by the infrastructure layer
 * and handled centrally by global exception handlers.
 * </p>
 */
public class InfraException extends BaseException {
    public InfraException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, errorCode.message(), cause);
    }
}