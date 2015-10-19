package org.alma.distributedforum.server.exception;

import org.alma.distributedforum.server.ISubject;

/**
 * Created on 9/30/15.
 *
 * @author dralagen
 */
public class SubjectAlreadyExist extends Exception {

    private static final long serialVersionUID = -8423837550054501570L;

    private ISubject existingSubject;

    public SubjectAlreadyExist(ISubject subject) {
        existingSubject = subject;
    }

    /**
     * Get the existing subject on forum server
     *
     * @return the existing subject
     */
    public ISubject getExistingSubject() {
        return existingSubject;
    }
}
