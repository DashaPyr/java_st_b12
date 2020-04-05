package dd.pyrkova.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("3138d8f359b4054ec5ec6278db7650392c11e8d0");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("DashaPyr", "java_st_b12")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
