package br.com.codesphere.jobs;

import br.com.codesphere.entities.JobControlEntity;
import br.com.codesphere.repositories.JobControlRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public abstract class AbstractJob {

  @Inject
  JobControlRepository jobControlRepository;

  protected abstract String jobName();

  protected abstract void process() throws Exception;

  @Transactional
  public final void execute() {

    JobControlEntity job = jobControlRepository.findByName(jobName().toLowerCase());

    if (job.isRunning) {
      System.out.println("[" + jobName() + "] Job already running");
      return;
    }

    lock(job);

    try {
      System.setProperty("java.net.preferIPv4Stack", "true");

      process();
    } catch (Exception ex) {
      System.out.println("[" + jobName() + "] ERROR: " + ex.getMessage());
    } finally {
      free(job);
    }
  }

  private void lock(JobControlEntity job) {
    job.isRunning = true;
    job.persist();
  }

  private void free(JobControlEntity job) {
    job.isRunning = false;
    job.persist();
  }
}
