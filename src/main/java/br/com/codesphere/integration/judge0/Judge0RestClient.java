package br.com.codesphere.integration.judge0;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.codesphere.integration.judge0.dtos.Judge0SubmissionRequestDTO;
import br.com.codesphere.integration.judge0.dtos.Judge0SubmissionResponseDTO;
import br.com.codesphere.integration.judge0.dtos.Judge0TokenDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "judge0")
@Produces(MediaType.APPLICATION_JSON)
public interface Judge0RestClient {

    @POST
    @Path("/submissions")
    Judge0TokenDTO createSubmission(@QueryParam("base64_encoded") boolean base64Encoded,
            @QueryParam("wait") boolean wait,
            Judge0SubmissionRequestDTO body);

    @GET
    @Path("/submissions/{token}")
    Judge0SubmissionResponseDTO findSubmission(@PathParam("token") String token,
            @QueryParam("base64_encoded") boolean base64Encoded,
            @QueryParam("fields") String fields);

}
