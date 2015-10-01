package org.alma.distributedforum.server.exception;

import org.alma.distributedforum.server.ISubject;

/**
 * Created on 9/30/15.
 *
 * @author dralagen
 */
public class SubjectAlreadyExist extends Exception {

    private ISubject existingSubject;

    public SubjectAlreadyExist(ISubject subject) {
        this.existingSubject = subject;
    }

    /**
     * Get the existing subject on forum server
     * @return the existing subject
     */
    public ISubject getExistingSubject() {
        return existingSubject;
    }
}
