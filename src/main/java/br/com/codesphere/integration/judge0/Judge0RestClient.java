package br.com.codesphere.integration.judge0;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.codesphere.integration.judge0.dtos.Judge0BatchSubmissionRequestDTO;
import br.com.codesphere.integration.judge0.dtos.Judge0TokenDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "judge0")
@Produces(MediaType.APPLICATION_JSON)
public interface Judge0RestClient {

    @POST
    @Path("/submissions/batch")
    List<Judge0TokenDTO> createBatchSubmission(@QueryParam("base64_encoded") boolean base64Encoded, Judge0BatchSubmissionRequestDTO body);

}
