package jp.co.liferay.headless.reservation.resource.v1_0;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import jp.co.liferay.headless.reservation.dto.v1_0.Participant;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/reservation-headless/v1.0
 *
 * @author Maurice Sepe
 * @generated
 */
@Generated("")
public interface ParticipantResource {

	public Page<Participant> getParticipantsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Participant postParticipant(Participant participant)
		throws Exception;

	public void deleteParticipant(String participantId) throws Exception;

	public Participant getParticipant(String participantId) throws Exception;

	public Participant putParticipant(
			String participantId, Participant participant)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}