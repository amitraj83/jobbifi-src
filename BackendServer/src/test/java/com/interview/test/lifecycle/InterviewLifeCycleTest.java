package com.interview.test.lifecycle;

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class InterviewLifeCycleTest {

/*  String encData = "";

  @Before
  public void initializeTest() {}

  @Test
  public void testDelete() {

  }

  @Test
  public void testDecrypt() {

    
     * String store; try { store = DataStoreRegistry.getInstance().getInterviewDataStore
     * ().getInterview("52320928a6a64a29b0250ca4").getInterviewee();
     * System.out.println("Interviewer : "+store); } catch (RemoteException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); }
     

    
     * ResetPasswordDataStore ds = new ResetPasswordDataStore(); ds.setExprired(new
     * ObjectId("525ebc45a6a6e885ca37d610"), true); ResetPasswordEntity entity =
     * ds.getResetPasswordEntity(new ObjectId("525ebc45a6a6e885ca37d610")); assertEquals(true,
     * entity.isExpired());
     
  }

  @Test
  public void testEncrypt() {

    
     * GenerateResetPasswordURLService gen = new GenerateResetPasswordURLService(); String url =
     * gen.generateURL("adam", "adam@tcd.ie"); String[] parts = url.split("[?]")[1].split("&");
     * String authInstance = ""; String authId = ""; String authToken = "";
     * 
     * for (String part : parts) { String[] kv = part.split("=");
     * if(kv[0].equals(VARIABLES.RESET_PASS.AUTH_ID)) authId = kv[1];
     * if(kv[0].equals(VARIABLES.RESET_PASS.AUTH_INSTANCE)) authInstance = kv[1];
     * if(kv[0].equals(VARIABLES.RESET_PASS.AUTH_TOKEN)) authToken = kv[1];
     * 
     * }
     * 
     * 
     * ResetPasswordDataStore ds = new ResetPasswordDataStore(); ResetPasswordEntity entity =
     * ds.getResetPasswordEntity(new ObjectId(authInstance)); String secKey = entity.getSecretKey();
     * // System.out.println("ObjectId : "+authInstance); // System.out.println("userid : "+authId);
     * // System.out.println("authToken : "+authToken); //
     * System.out.println("secretKey : "+secKey);
     * 
     * assertEquals(authInstance, entity.getId());
     * 
     * ConversionService con = new ConversionService();
     * 
     * DecryptionService decService = new DecryptionService(); String decryptedUserName =
     * decService.decrypt(new String(con.hexStringToByteArray(authId)), secKey);
     * assertEquals(decryptedUserName, entity.getUsername()); assertEquals(authToken,
     * entity.getSectoken());
     * 
     * 
     * 
     * ds.delete(new ObjectId( entity.getId()));
     

  }

  
   * 
   * private ObjectId iid; private ObjectId bid_id; private Interview interview; private Bid bid;
   * InterviewerDataStore iids = new InterviewerDataStore();
   * 
   * @Before public void initializeTest(){
   * 
   * 
   * 
   * InterviewDataStore ids = new InterviewDataStore(); interview = new Interview();
   * interview.setDescription("Test Description"); interview.setDt(new Date().getTime());
   * interview.setInterviewee("adam"); interview.setSkills("Java");
   * interview.setStatus(INTERVIEW_STATUS.PENDING); interview.setTitle("Test Java Interview"); iid =
   * ids.saveInterview(interview);
   * 
   * BidStore bs = new BidStore(); bid = new Bid(); bid.setBidder("atul"); bid.setDate(new
   * Date().getTime()); bid.setIid(iid.toString()); bid.setMsg("I can take your interview");
   * bid.setPrice("30"); bid.setStatus(BID_STATUS.PENDING); bid_id = bs.saveBid(bid);
   * 
   * }
   * 
   * 
   * @Autowired AwardInterviewHandler awardInterviewHandler;
   * 
   * @Test public void testDelete(){
   * 
   * InterviewDataStore ids = new InterviewDataStore(); ids.deleteInterivew(iid); Interview i =
   * ids.getInterview(iid); assertNull(i); }
   * 
   * @Autowired private EditInterview EditInterview;
   * 
   * @Test public void testEdit(){ InterviewDataStore ids = new InterviewDataStore(); Map<String,
   * Object> changes = new HashMap<String, Object>(); {
   * 
   * changes.put(DATASTORES.INTERVIEW.DESCRIPTION, "My New Java Interview"); Interview prev_i =
   * ids.getInterview(iid); EditInterview.editInterview(iid, changes); Interview i =
   * ids.getInterview(iid); assertEquals(i.getDescription(), "My New Java Interview");
   * assertEquals(prev_i.getSkills(), i.getSkills()); assertEquals(prev_i.getStatus(),
   * i.getStatus()); } { changes = new HashMap<String, Object>();
   * changes.put(DATASTORES.INTERVIEW.INTERVIEWEE, "adam"); Interview prev_i =
   * ids.getInterview(iid); EditInterview.editInterview(iid, changes); Interview updated_i =
   * ids.getInterview(iid); assertEquals(updated_i.getDescription(), prev_i.getDescription());
   * assertEquals(updated_i.getSkills(), prev_i.getSkills()); assertEquals(updated_i.getStatus(),
   * prev_i.getStatus()); assertEquals(updated_i.getInterviewee(), prev_i.getInterviewee()); }
   * 
   * { changes = new HashMap<String, Object>(); changes.put(DATASTORES.INTERVIEW.INTERVIEWER,
   * "stephen"); Interview prev_i = ids.getInterview(iid); EditInterview.editInterview(iid,
   * changes); Interview updated_i = ids.getInterview(iid); assertEquals(updated_i.getDescription(),
   * prev_i.getDescription()); assertEquals(updated_i.getSkills(), prev_i.getSkills());
   * assertEquals(updated_i.getStatus(), prev_i.getStatus());
   * assertEquals(updated_i.getInterviewee(), prev_i.getInterviewee());
   * assertEquals(updated_i.getInterviewer(), "stephen"); } { changes = new HashMap<String,
   * Object>(); changes.put(DATASTORES.INTERVIEW.SKILLS, "MySQL Java HIbernate"); Interview prev_i =
   * ids.getInterview(iid); EditInterview.editInterview(iid, changes); Interview updated_i =
   * ids.getInterview(iid); assertEquals(updated_i.getDescription(), prev_i.getDescription());
   * assertEquals(updated_i.getSkills(), "MySQL Java HIbernate");
   * assertEquals(updated_i.getStatus(), prev_i.getStatus());
   * assertEquals(updated_i.getInterviewee(), prev_i.getInterviewee());
   * assertEquals(updated_i.getInterviewer(), prev_i.getInterviewer()); }
   * 
   * { changes = new HashMap<String, Object>(); changes.put(DATASTORES.INTERVIEW.STATUS,
   * BID_STATUS.ACCEPT); Interview prev_i = ids.getInterview(iid); EditInterview.editInterview(iid,
   * changes); Interview updated_i = ids.getInterview(iid); assertEquals(updated_i.getDescription(),
   * prev_i.getDescription()); assertEquals(updated_i.getSkills(), "MySQL Java HIbernate");
   * assertEquals(updated_i.getStatus(), BID_STATUS.ACCEPT);
   * assertEquals(updated_i.getInterviewee(), prev_i.getInterviewee());
   * assertEquals(updated_i.getInterviewer(), prev_i.getInterviewer()); } }
   * 
   * @Autowired private CancelInterview CancelInterview;
   * 
   * @Test public void testCancel(){ InterviewDataStore ids = new InterviewDataStore();
   * 
   * Interview prev_i = ids.getInterview(iid); CancelInterview.cancelInterview(iid); Interview i =
   * ids.getInterview(iid); assertEquals(i.getDescription(), prev_i.getDescription());
   * assertEquals(prev_i.getSkills(), i.getSkills()); assertEquals(i.getStatus(),
   * INTERVIEW_STATUS.CANCELLED);
   * 
   * }
   * 
   * @Autowired private RepostInterview RepostInterview;
   * 
   * @Test public void testRepost(){ InterviewDataStore ids = new InterviewDataStore(); Interview
   * prev = ids.getInterview(iid); ObjectId newId = RepostInterview.repostInterview(iid); Interview
   * next = ids.getInterview(newId); assertEquals(prev.getTitle()+" (repost)", next.getTitle());
   * assertNotSame(prev.getDt(), next.getDt()); assertEquals(prev.getInterviewee(),
   * next.getInterviewee()); ids.deleteInterivew(newId);
   * 
   * }
   * 
   * @Autowired private BidAccept bidAccept;
   * 
   * @Test public void bidAccept(){
   * 
   * bidAccept.acceptBid(iid, bid_id); InterviewDataStore ids = new InterviewDataStore(); Interview
   * i = ids.getInterview(iid); BidStore bs = new BidStore(); Bid newBid = bs.getBid(bid_id);
   * assertEquals(i.getInterviewer(), bid.getBidder()); assertEquals(newBid.getStatus(),
   * BID_STATUS.ACCEPT); assertEquals(i.getStatus(), INTERVIEW_STATUS.IN_PROGRESS);
   * 
   * }
   * 
   * @Autowired private EscrowDeposit EscrowDeposit;
   * 
   * @Test public void testEscrowDeposit(){
   * 
   * iids.updateBalance(interview.getInterviewee(), 50, VARIABLES.ADD); Map<String, Object> userInfo
   * = iids.getUserInfo(interview.getInterviewee()); assertEquals(new
   * Double(userInfo.get(USER.BALANCE).toString()), new Double(50));
   * 
   * EscrowDeposit.escrowDeposit(iid, 32.3); InterviewDataStore ids = new InterviewDataStore();
   * Interview i = ids.getInterview(iid); assertNotSame(i.getStatus(),
   * INTERVIEW_STATUS.ESCROW_DEPOSITED); bidAccept.acceptBid(iid, bid_id);
   * EscrowDeposit.escrowDeposit(iid, 32.3); Interview newI = ids.getInterview(iid);
   * assertEquals(newI.getStatus(), INTERVIEW_STATUS.ESCROW_DEPOSITED);
   * 
   * iids.setBalance(interview.getInterviewee(), 0);
   * 
   * }
   * 
   * @Autowired private ReleaseFunds ReleaseFunds;
   * 
   * @Test public void testReleaseFunds(){
   * 
   * 
   * InterviewDataStore ids = new InterviewDataStore(); InterviewerDataStore iids = new
   * InterviewerDataStore();
   * 
   * int status = ReleaseFunds.releaseFunds(iid, 42.8); assertEquals(status,
   * RETURN_VALUES.RELEASE_FUNDS_FAIL);
   * 
   * bidAccept.acceptBid(iid, bid_id);
   * 
   * status = ReleaseFunds.releaseFunds(iid, 42.8); assertEquals(status,
   * RETURN_VALUES.INSUFFICIENT_BALANCE);
   * 
   * iids.updateBalance(interview.getInterviewee(), 50, VARIABLES.ADD); Map<String, Object> userInfo
   * = iids.getUserInfo(interview.getInterviewee()); assertEquals(new
   * Double(userInfo.get(USER.BALANCE).toString()), new Double(50));
   * 
   * EscrowDeposit.escrowDeposit(iid, 32.3); Interview i = new
   * InterviewDataStore().getInterview(iid); assert(i.getEb() == new Double(32.3).doubleValue());
   * userInfo = iids.getUserInfo(interview.getInterviewee()); assertEquals(new
   * Double(userInfo.get(USER.BALANCE).toString()), new Double(17.7), 0.001);
   * 
   * 
   * ReleaseFunds.releaseFunds(iid, 30.5); userInfo = iids.getUserInfo(interview.getInterviewee());
   * Interview i2 = new InterviewDataStore().getInterview(iid); assertEquals(new
   * Double(userInfo.get(USER.BALANCE).toString()), new Double(17.7), 0.001);
   * assertEquals(i2.getEb(), new Double(1.8).doubleValue(),0.001);
   * 
   * userInfo = iids.getUserInfo(i2.getInterviewer()); //assertEquals(new
   * Double(userInfo.get(USER.BALANCE).toString()), new Double(30.5));
   * iids.setBalance(i2.getInterviewer(), 0);
   * 
   * }
   * 
   * @After public void remove(){
   * 
   * 
   * iids.setBalance(interview.getInterviewee(), 0);
   * 
   * InterviewDataStore ids = new InterviewDataStore(); ids.deleteInterivew(iid);
   * 
   * BidStore bs = new BidStore(); bs.deleteBid(bid_id); }
   
  @After
  public void remove() {

  }
*/}
