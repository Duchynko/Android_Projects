package com.example.android.miwok;

/**
 * Created by jakub on 11.8.2017.
 */

public class Word {

    //AudioFile
    private int mAudioResourceId;

    //Default translation of the word
    private String mdefaultTranslation;

    //Miwok translation of the word
    private String mMiwokTranslation;

    //Miwok translation of the word
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;


    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId){
        mdefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     *
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        mdefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    //Get default translation of the word
    public String getDefaultTranslation(){

        return mdefaultTranslation;
    }

    //Get Miwok translation of the word
    public String getmMiwokTranslation(){

        return mMiwokTranslation;
    }

    //Get Miwok translation of the word
    public int getmImageResourceId(){

        return mImageResourceId;
    }

    public boolean hasImage(){

        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


}
