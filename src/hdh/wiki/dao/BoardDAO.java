package hdh.wiki.dao;

public class BoardDAO {

    private int contentsIdx; // 게시판 인덱스

    private String contentsTitle; // 제목
    private String writerId; // 작성자
    private String contentsText; // 내용
    private String writeDate; // 작성 일자
    private String editDate; // 수정 일자
    private int hit; // 조회수

    public BoardDAO() {
    }

    public BoardDAO(int IDX, String TITLE, String WRITERID, String CTEXT, int HIT, String DATE) {

        this.contentsIdx = IDX;
        this.contentsTitle = TITLE;
        this.writerId = WRITERID;
        this.contentsText = CTEXT;
        this.hit = HIT;
        this.writeDate = DATE;
    }

    public BoardDAO(int contentsIdx, String contentsTitle, String writerId, String writeDate, int hit) {
        super();
        this.contentsIdx = contentsIdx;
        this.contentsTitle = contentsTitle;
        this.writerId = writerId;
        this.writeDate = writeDate;
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "BoardDAO [contentsIdx=" + contentsIdx + ", contentsTitle=" + contentsTitle + ", writerId="
                + writerId + ", writeDate=" + writeDate + ", hit=" + hit + "]";
    }

    public int getContentsIdx() {
        return contentsIdx;
    }

    public void setContentsIdx(int contentsIdx) {
        this.contentsIdx = contentsIdx;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setwriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getContentsTitle() {
        return contentsTitle;
    }

    public void setContentsTitle(String contentsTitle) {
        this.contentsTitle = contentsTitle;
    }

    public String getContentsText() {
        return contentsText;
    }

    public void setContentsText(String contentsText) {
        this.contentsText = contentsText;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

}