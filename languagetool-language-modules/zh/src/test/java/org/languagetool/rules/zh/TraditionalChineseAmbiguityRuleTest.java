/* LanguageTool, a natural language style checker
 * Copyright (C) 2013 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */

package org.languagetool.rules.zh;

import org.languagetool.JLanguageTool;
import org.languagetool.language.TraditionalChinese;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TraditionalChineseAmbiguityRuleTest {

  JLanguageTool langTool = new JLanguageTool(new TraditionalChinese());
  Rule rule = new TraditionalChineseAmbiguityRule();

  @Test
  public void test() throws IOException {

    String s1 = "「以後等妳當上皇后，就能買馬鈴薯慶祝了」";
    assertTrue(s1);

    String s2 = "“以後等妳當上皇後，就能買土豆慶祝了”";
    String[] corrections = {"“", "皇后","","”"};
    assertCorrection(s2, corrections);
  }

  private void assertTrue(String sentence) throws IOException {
    RuleMatch[] result = rule.match(langTool.getAnalyzedSentence(sentence));
    assertEquals(0, result.length);
  }

  private void assertCorrection(String sentence, String[] corrections) throws IOException {
    RuleMatch[] ruleMatches = rule.match(langTool.getAnalyzedSentence(sentence));
    for (int i = 0; i < ruleMatches.length; i++) {
      assertEquals(ruleMatches[i].getSuggestedReplacements().get(0), corrections[i]);
    }
  }
}
