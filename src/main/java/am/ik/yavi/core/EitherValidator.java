package am.ik.yavi.core;
/*
 * Copyright (C) 2018-2021 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Locale;

import am.ik.yavi.fn.Either;

/**
 * Either validator class
 * @param <T> Target class
 * @since 0.6.0
 */
public final class EitherValidator<T> {
	private final Validator<T> validator;

	EitherValidator(Validator<T> validator) {
		this.validator = validator;
	}

	/**
	 * Validates all constraints on {@code target} and returns {@code Either} object that
	 * has constraint violations on the left or validated object on the right. <br>
	 * {@code Locale.getDefault()} is used to locate the violation messages.<br>
	 * {@code ConstraintGroup.DEFAULT} is used as a constraint group.
	 *
	 * @param target target to validate
	 * @return either object that has constraint violations on the left or validated
	 * object on the right
	 * @throws IllegalArgumentException if target is {@code null}
	 */
	public Either<ConstraintViolations, T> validate(T target) {
		return this.validate(target, Locale.getDefault(), ConstraintGroup.DEFAULT);
	}

	/**
	 * Validates all constraints on {@code target} and returns {@code Either} object that
	 * has constraint violations on the left or validated object on the right. <br>
	 * {@code Locale.getDefault()} is used to locate the violation messages.
	 *
	 * @param target target to validate
	 * @param constraintGroup constraint group to validate
	 * @return either object that has constraint violations on the left or validated
	 * object on the right
	 * @throws IllegalArgumentException if target is {@code null}
	 */
	public Either<ConstraintViolations, T> validate(T target,
			ConstraintGroup constraintGroup) {
		return this.validate(target, Locale.getDefault(), constraintGroup);
	}

	/**
	 * Validates all constraints on {@code target} and returns {@code Either} object that
	 * has constraint violations on the left or validated object on the right. <br>
	 * {@code ConstraintGroup.DEFAULT} is used as a constraint group.
	 *
	 * @param target target to validate
	 * @param locale the locale targeted for the violation messages.
	 * @return either object that has constraint violations on the left or validated
	 * object on the right
	 * @throws IllegalArgumentException if target is {@code null}
	 */
	public Either<ConstraintViolations, T> validate(T target, Locale locale) {
		return this.validate(target, locale, ConstraintGroup.DEFAULT);
	}

	/**
	 * Validates all constraints on {@code target} and returns {@code Either} object that
	 * has constraint violations on the left or validated object on the right. <br>
	 *
	 * @param target target to validate
	 * @return either object that has constraint violations on the left or validated
	 * object on the right
	 * @throws IllegalArgumentException if target is {@code null}
	 */
	public Either<ConstraintViolations, T> validate(T target, Locale locale,
			ConstraintGroup constraintGroup) {
		ConstraintViolations violations = this.validator.validate(target, locale,
				constraintGroup);
		if (violations.isValid()) {
			return Either.right(target);
		}
		else {
			return Either.left(violations);
		}
	}
}
