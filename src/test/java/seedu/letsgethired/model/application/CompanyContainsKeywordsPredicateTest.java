package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.testutil.InternApplicationBuilder;

public class CompanyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyContainsKeywordsPredicate firstPredicate =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        CompanyContainsKeywordsPredicate secondPredicate =
                new CompanyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyContainsKeywordsPredicate firstPredicateCopy =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different intern application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Alice Bob").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Alice Bob").build()));

        // Only one matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Alice Bob").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Alice").build()));

        // Non-matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Alice Bob").build()));

        // Keywords match role, cycle and status, but does not match company
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("SWE", "Intern", "Summer", "Accepted"));
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Alice").withRole("SWE Intern")
                .withCycle("Summer").withStatus("Accepted").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(keywords);

        String expected = CompanyContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}