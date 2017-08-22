/*
 * Copyright (c) 2017 Play Music Exporter Authors
 *
 * This is licensed under the GPLv3, see LICENSE.
 *
 *
 * An earlier version of this Software is available under the MIT License,
 * written by David Schulte in 2015, 
 * and can be downloaded here: https://github.com/Arcus92/PlayMusicExporter
 */

package re.jcg.superuser;

import java.util.ArrayList;
import java.util.List;

/**
 * A dynamic growing byte buffer
 */
public class ByteBuffer {
    private int mChunkSize = 4096;
    private int mSize = 0;
    private List<byte[]> mChunks = new ArrayList<>();

    /**
     * Creates a byte buffer
     */
    public ByteBuffer() {
    }

    /**
     * Creates a byte buffer
     * @param chunkSize Sets the chunks size
     */
    public ByteBuffer(int chunkSize) {
        mChunkSize = chunkSize;
    }
    /**
     * @return Returns the size of the buffer
     */
    public long size() {
        return mSize;
    }

    /**
     * Clears the buffer
     */
    public void clear() {
        mSize = 0;
        mChunks.clear();
    }

    /**
     * Adds bytes to the buffer
     * @param bytes The bytes to add
     */
    public void append(byte[] bytes, int offset, int length) {


        while(length > 0) {
            int freeSpaceInEndChunk = mChunks.size() * mChunkSize - mSize;
            int positionInEndChunk = mChunkSize - freeSpaceInEndChunk;

            // Adds a new chunk
            if (freeSpaceInEndChunk == 0) {
                mChunks.add(new byte[mChunkSize]);
                freeSpaceInEndChunk = mChunkSize;
                positionInEndChunk = 0;
            }
            byte[] endChunk = mChunks.get(mChunks.size() - 1);

            // We want to add more bytes the chunk can contain
            if (length > freeSpaceInEndChunk) {

                // Copies to end of chunk
                System.arraycopy(bytes, offset, endChunk, positionInEndChunk, freeSpaceInEndChunk);

                // Adds the size
                mSize += freeSpaceInEndChunk;
                offset += freeSpaceInEndChunk;
                length -= freeSpaceInEndChunk;
            } else {
                // Copies the rest of the data
                System.arraycopy(bytes, offset, endChunk, positionInEndChunk, length);

                // Adds the size
                mSize += length;

                offset += length;
                length = 0;
            }
        }
    }

    /**
     * @return Returns the bytes as array
     */
    public byte[] toByteArray() {
        byte[] bytes = new byte[mSize];

        int pos = 0;

        // Copies all chunks
        for(byte[] subBytes : mChunks) {
            int chunkSize = mChunkSize;
            // Last segment
            if (pos + chunkSize > mSize)
                chunkSize = mSize - pos;

            // Copies the bytes
            System.arraycopy(subBytes, 0, bytes, pos, chunkSize);

            pos += chunkSize;
        }

        return bytes;
    }
}
