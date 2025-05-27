package student.management.system;
public class Stream {
    private int streamId;
    private String streamName;
    private int deptId;

    public Stream(int streamId, String streamName, int deptId) {
        this.streamId = streamId;
        this.streamName = streamName;
        this.deptId = deptId;
    }

    public Stream(String streamName, int deptId) {
        this.streamName = streamName;
        this.deptId = deptId;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "streamId=" + streamId +
                ", streamName='" + streamName + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
