package com.example.assignment2


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FunctionTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun functionTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.cancelButton), withText("Cancel"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val radioButton = onView(
            allOf(
                withText("Sustain Pedal"),
                childAtPosition(
                    allOf(
                        withId(R.id.instrumentAttributeGroup),
                        childAtPosition(
                            withId(R.id.main),
                            5
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        radioButton.perform(click())

        val radioButton2 = onView(
            allOf(
                withText("Bench"),
                childAtPosition(
                    allOf(
                        withId(R.id.instrumentAttributeGroup),
                        childAtPosition(
                            withId(R.id.main),
                            5
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        radioButton2.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val radioButton3 = onView(
            allOf(
                withText("Sticks Included"),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowInstrumentAttributeGroup),
                        childAtPosition(
                            withId(R.id.main),
                            7
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        radioButton3.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val materialButton9 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val materialButton10 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())

        val radioButton4 = onView(
            allOf(
                withText("Sticks Included"),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowInstrumentAttributeGroup),
                        childAtPosition(
                            withId(R.id.main),
                            7
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        radioButton4.perform(click())

        val materialButton11 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton11.perform(click())

        val materialButton12 = onView(
            allOf(
                withId(R.id.cancelButton), withText("Cancel"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton12.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
