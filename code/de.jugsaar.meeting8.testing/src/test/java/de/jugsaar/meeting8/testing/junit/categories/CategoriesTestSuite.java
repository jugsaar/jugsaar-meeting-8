package de.jugsaar.meeting8.testing.junit.categories;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @see https://github.com/junit-team/junit/wiki/Categories
 */
@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@ExcludeCategory(FastTests.class)
@SuiteClasses({ A.class, B.class })
public class CategoriesTestSuite {
	// Will run A.b, but not A.a or B.c
}
