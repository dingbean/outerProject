package cn.sh.outer.model.resp;


/**
 * 音乐消息
 * 
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

	@Override
	public String toString() {
		return "MusicMessage [Music=" + Music + ", toString()="
				+ super.toString() + "]";
	}

	
	
}