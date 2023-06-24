package support.dto;

import java.sql.Timestamp;

public class Job {
	
	
	private int jobId;
	private String companyName;
	private String jobUserName;
	private String jobHeader;
	private String jobHeaderWithCompanyName;
	private String jobText;
	private int jobSalary;
	private Timestamp jobPostedDate;
	private int jobUserId;
	
	public Job(int jobId,String companyName, String jobHeader, String jobText, int jobSalary, Timestamp jobPostedDate, int jobUserId) {
		super();
		this.jobId = jobId;
		this.companyName = companyName;
		this.jobHeader = jobHeader;
		this.jobText = jobText;
		this.jobSalary = jobSalary;
		this.jobPostedDate = jobPostedDate;
		this.jobUserId = jobUserId;
	}
	
	public Job() {}
	
	public String getJobHeaderWithCompanyName() {
		this.jobHeaderWithCompanyName = this.jobHeader + " (" + this.companyName + ")";
		return jobHeaderWithCompanyName;
	}
	
	public int getJobId() {
		return jobId;
	}
	public Job setJobId(int jobId) {
		this.jobId = jobId;
		return this;
	}
	
	public String getJobHeader() {
		return jobHeader;
	}
	
	
	public Job setJobHeader(String jobHeader) {
		this.jobHeader = jobHeader;
		return this;
	}
	public String getJobText() {
		return jobText;
	}
	public Job setJobText(String jobText) {
		this.jobText = jobText;
		return this;
	}
	public int getJobSalary() {
		return jobSalary;
	}
	public Job setJobSalary(int jobSalary) {
		this.jobSalary = jobSalary;
		return this;
	}
	public Timestamp getJobPostedDate() {
		return jobPostedDate;
	}
	public Job setJobPostedDate(Timestamp jobPostedDate) {
		this.jobPostedDate = jobPostedDate;
		return this;
	}
	public int getJobUserId() {
		return jobUserId;
	}
	public Job setJobUserId(int jobUserId) {
		this.jobUserId = jobUserId;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Job setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getJobUserName() {
		return jobUserName;
	}

	public Job setJobUserName(String jobUserName) {
		this.jobUserName = jobUserName;
		return this;
	}
	
	
	
}
