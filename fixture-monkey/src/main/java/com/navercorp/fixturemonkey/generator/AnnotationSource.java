/*
 * Fixture Monkey
 *
 * Copyright (c) 2021-present NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.fixturemonkey.generator;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

import net.jqwik.api.Arbitrary;

public final class AnnotationSource<T> {
	private final Class<T> clazz;
	private final Arbitrary<T> arbitrary;
	private final List<Annotation> annotations;

	public AnnotationSource(
		Class<T> clazz,
		Arbitrary<T> arbitrary,
		List<Annotation> annotations
	) {
		this.clazz = clazz;
		this.arbitrary = arbitrary;
		this.annotations = annotations;
	}

	public Arbitrary<T> getArbitrary() {
		return arbitrary;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public <U extends Annotation> Optional<U> findAnnotation(Class<U> annotationClass) {
		return (Optional<U>)annotations.stream()
			.filter(it -> it.annotationType() == annotationClass)
			.findAny();
	}
}
