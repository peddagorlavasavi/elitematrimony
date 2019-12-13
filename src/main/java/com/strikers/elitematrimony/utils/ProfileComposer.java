package com.strikers.elitematrimony.utils;

/**
 * 
 * @author Sujal
 *
 * @param <I>
 * @param <O>
 */
public interface ProfileComposer<I, O> {
	
	O compose(I entity);
}
