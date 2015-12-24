package com.interview.util.conversion;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.USER;
import com.interview.solr.pojo.SolrInterviewer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class InterviewerConversion {

  public static SolrInterviewer getInterviewerForUpdate(BasicDBObject data) {

    SolrInterviewer interviewer =
        getInterviewerPojo(data.getString("_id"), (BasicDBObject) data.get("$set"));
    return interviewer;
  }

  public static SolrInterviewer getInterviewerForInsert(BasicDBObject data) {

    SolrInterviewer interviewer = getInterviewerPojo(data.getString("_id"), data);
    return interviewer;
  }

  private static SolrInterviewer getInterviewerPojo(String _id, DBObject data) {
    SolrInterviewer interviewer = new SolrInterviewer();

    interviewer.setId(_id);

    DBObject row = MongoDBService.getInstance().getInterviewerCollection()
        .findOne(new BasicDBObject("_id", new ObjectId(_id)));

    if (data.get(USER.USERNAME) != null)
      interviewer.setUsername(data.get(USER.USERNAME).toString());
    else
      interviewer.setUsername(row.get(USER.USERNAME).toString());

    if (data.get(USER.COUNTRY) != null)
      interviewer.setCountry(data.get(USER.COUNTRY).toString());
    else
      interviewer.setCountry(row.get(USER.COUNTRY).toString());

    if (data.get(USER.CV) != null)
      interviewer.setCv(data.get(USER.CV).toString());
    else
      interviewer.setCv(row.get(USER.CV).toString());

    interviewer.setDocType(row.get(USER.TYPE).toString());

    if (data.get(USER.EDUCATIONS) != null) {
      BasicDBList list = (BasicDBList) data.get(USER.EDUCATIONS);
      String[] degrees = new String[list.size()];
      String[] fieldOfStudy = new String[list.size()];
      String[] schools = new String[list.size()];

      for (int i = 0; i < list.size(); i++) {
        String educationId = list.get(i).toString();
        DBObject query = new BasicDBObject("_id", new ObjectId(educationId));
        DBObject obj = MongoDBService.getInstance().getEducationCollection().findOne(query);

        fieldOfStudy[i] = obj.get(DATASTORES.EDUCATION.FIELDOFSTUDY).toString();
        schools[i] = obj.get(DATASTORES.EDUCATION.SCHOOLNAME).toString();
        degrees[i] = obj.get(DATASTORES.EDUCATION.DEGREE).toString();
      }
      interviewer.setDegrees(degrees);
      interviewer.setFieldOfStudies(fieldOfStudy);
      interviewer.setSchoolnames(schools);
    }

    if (data.get(USER.INDUSTRY) != null) {
      BasicDBList list = (BasicDBList) data.get(USER.INDUSTRY);
      String[] industries = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        industries[i] = list.get(i).toString();
      }
      interviewer.setIndustries(industries);
    } else
      interviewer.setIndustries(new String[] {});

    if (data.get(USER.POSITIONS) != null) {
      BasicDBList list = (BasicDBList) data.get(USER.POSITIONS);

      String[] works = new String[list.size()];
      String[] titles = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        String posId = list.get(i).toString();
        DBObject query = new BasicDBObject("_id", new ObjectId(posId));
        DBObject obj = MongoDBService.getInstance().getPositionCollection().findOne(query);
        works[i] = obj.get(DATASTORES.POSITION.DESCRIPTION).toString();
        titles[i] = obj.get(DATASTORES.POSITION.TITLE).toString();
      }
      interviewer.setTitles(titles);
      interviewer.setDescriptions(works);
    }

    if (data.get(USER.RATE) != null)
      interviewer.setRate(new Integer(data.get(USER.RATE).toString()));

    if (data.get("skillids") != null) {
      BasicDBList SkillIDs = (BasicDBList) data.get("skillids");
      String[] skills = new String[SkillIDs.size()];
      for (int i = 0; i < SkillIDs.size(); i++) {
        String skillId = SkillIDs.get(i).toString();
        DBObject query = new BasicDBObject("_id", new ObjectId(skillId));
        DBObject obj = MongoDBService.getInstance().getSkillsCollection().findOne(query);
        skills[i] = obj.get(DATASTORES.SKILL.SKILL).toString();
      }
      interviewer.setSkills(skills);
    }
    return interviewer;
  }
}
